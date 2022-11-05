//first
const axios = require("axios");
const express = require("express");
const app = express();
const port = 3000;

const teams = [
    {
        name: "c9",
        roster: ["fudge", "blaber", "perkz", "zven", "vulcan"]
    },
    {
        name: "skt",
        roster: ["marin", "bengi", "faker", "bang", "wolf"]
    }
];

app.get('/getfinalscore/:username', (req, res) => {
    var total = [];
    var sum = 0;
    teams.forEach(search => {
        if (search.name === req.params.username) {
            search.roster.forEach(players => {
                axios.get(`http://localhost:4200/getplayerscore/${players}`)
                    .then(resp => {
                        total.push(resp.data.score);
                        if (total.length == 5) {
                            for (var a = 0; a < total.length; a++) {
                                sum += total[a];
                            }
                            res.send(search.name + " Had a winrate of: " + sum + " % out of 500");
                        }
                    }
                    )
                    .catch(error => console.log(error))
            });
        }

    });

});

app.listen(port, () => {
    console.log(`The server has started and is listening on http://localhost:${port}`);
});