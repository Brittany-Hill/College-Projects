package com.example.petheart

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.view.*
import android.widget.*
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import java.io.File
import java.util.*

private const val TAG = "MemoryFragment"
private const val ARG_MEMORY_ID = "memory_id"
private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0
private const val REQUEST_PHOTO = 2
private const val DATE_FORMAT = "EEE, MMM, dd"

class MemoryFragment : Fragment(),
    DatePickerFragment.Callbacks {

    private lateinit var memory: Memory
    private lateinit var photoFile: File
    private lateinit var photoUri: Uri
    private lateinit var nameField: EditText
    private lateinit var dateButton: Button
    private lateinit var photoDescription: EditText
    private lateinit var favoriteSwitch: Switch
    private lateinit var photoView: ImageView
    private lateinit var cameraButton: ImageButton
    private val memoryViewModel: MemoryViewModel by lazy {
        ViewModelProviders.of(this).get(MemoryViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        memory = Memory()
        val memoryId: UUID = arguments?.getSerializable(ARG_MEMORY_ID) as UUID
        memoryViewModel.loadMemory(memoryId)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_memory, container, false)


        nameField = view.findViewById(R.id.photo_name) as EditText
        dateButton = view.findViewById(R.id.date_button) as Button
        favoriteSwitch = view.findViewById(R.id.favorite_switch) as Switch
        photoDescription = view.findViewById(R.id.photo_description) as EditText
        cameraButton = view.findViewById(R.id.camera_button) as ImageButton
        photoView = view.findViewById(R.id.pet_photo) as ImageView


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val memoryId = arguments?.getSerializable(ARG_MEMORY_ID) as UUID
        memoryViewModel.loadMemory(memoryId)
        memoryViewModel.memoryLiveData.observe(
            viewLifecycleOwner,
            Observer { memory ->
                memory?.let {
                    this.memory = memory
                    photoFile = memoryViewModel.getPhotoFile(memory)
                    photoUri = FileProvider.getUriForFile(
                        requireActivity(),
                        "com.example.petheart.fileprovider",
                        photoFile
                    )
                    updateUI()
                }

            })

    }

    override fun onStart() {
        super.onStart()

        val titleWatcher = object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                memory.name = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        val descriptionWatcher = object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                memory.description = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        nameField.addTextChangedListener(titleWatcher)
        photoDescription.addTextChangedListener(descriptionWatcher)
        dateButton.setOnClickListener {
            DatePickerFragment.newInstance(memory.date)
                .apply {
                    setTargetFragment(
                        this@MemoryFragment,
                        REQUEST_DATE
                    )
                    show(
                        this@MemoryFragment.requireFragmentManager(),
                        DIALOG_DATE
                    )
                }
        }
        favoriteSwitch.apply {
            setOnCheckedChangeListener { _, isChecked ->
                memory.favorite = isChecked
            }
        }
        cameraButton.apply {
            val packageManager: PackageManager = requireActivity().packageManager
            val captureImage = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val resolvedActivity: ResolveInfo? =
                packageManager.resolveActivity(
                    captureImage,
                    PackageManager.MATCH_DEFAULT_ONLY
                )
            if (resolvedActivity == null) {
                isEnabled = false
            }
            setOnClickListener {
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)

                val cameraActivities: List<ResolveInfo> =
                    packageManager.queryIntentActivities(
                        captureImage,
                        PackageManager.MATCH_DEFAULT_ONLY
                    )

                for (cameraActivity in cameraActivities) {
                    requireActivity().grantUriPermission(
                        cameraActivity.activityInfo.packageName,
                        photoUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
                startActivityForResult(
                    captureImage,
                    REQUEST_PHOTO
                )
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_create_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_memory -> {
                memoryViewModel.deleteMemory(memory)
                requireActivity().onBackPressed()
                true
            }
            R.id.share_memory -> {
                Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                }.also { intent ->
                    val chooserIntent =
                        Intent.createChooser(intent, getSharedMemory())
                    startActivity(chooserIntent)
                }
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun getSharedMemory(): String {
        val dateString = DateFormat.format(DATE_FORMAT, memory.date).toString()
        return if (memory.description.isBlank()) {
            getString(R.string.memory_summary_no_descript, memory.name, dateString)
        } else {
            getString(R.string.memory_summary_descript, memory.name, dateString, memory.description)
        }
    }

    override fun onStop() {
        super.onStop()
        memoryViewModel.saveMemory(memory)
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().revokeUriPermission(
            photoUri,
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        )
    }

    override fun onDateSelected(date: Date) {
        memory.date = date
        updateUI()
    }

    private fun updateUI() {
        nameField.setText(memory.name)
        dateButton.text = memory.date.toString()
        favoriteSwitch.apply {
            isChecked = memory.favorite
            jumpDrawablesToCurrentState()
        }
        if (memory.description.isNotEmpty()) {
            photoDescription.setText(memory.description)
        }
        updatePhotoView()
    }

    private fun updatePhotoView() {
        if (photoFile.exists()) {
            val bitmap = getScaledBitmap(
                photoFile.path,
                requireActivity()
            )
            photoView.setImageBitmap(bitmap)
        } else {
            photoView.setImageDrawable(null)
        }
    }


    companion object {
        fun newInstance(memoryId: UUID): MemoryFragment {
            val args = Bundle().apply {
                putSerializable(ARG_MEMORY_ID, memoryId)
            }
            return MemoryFragment().apply {
                arguments = args
            }
        }
    }

}