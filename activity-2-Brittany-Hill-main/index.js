//second
const express = require("express");
const app = express();
const port = 4200;
const axios = require("axios");
//Using Winloss rate as the final score 
var players = [
    { name: "fudge", score: 67 },
    { name: "blaber", score: 58 },
    { name: "perkz", score: 58 },
    { name: "zven", score: 64 },
    { name: "vulcan", score: 58 },
    { name: "marin", score: 88 },
    { name: "bengi", score: 71 },
    { name: "faker", score: 58 },
    { name: "bang", score: 61 },
    { name: "wolf", score: 69 }

];
app.get('/getplayerscore/:username', (req, res) => {
    console.log("Query: " + req.params.username);
    players.forEach(player => {
        if (player.name === req.params.username) {
            //console.log(player.score);
            //console.log(player);
            res.json({ score: player.score });
        }

    });

});

app.listen(port, () => {
    console.log(`The server has started and is listening on http://localhost:${port}`);
});
