/**
 * Controller Of API
 * @since 0.2
 */

var Model = require('../model/models')

/**
 * Get Player Ranking
 * @param username - string
 */
module.exports.getPlayerRank = function(req, res){
  var lusername = req.body.username;

  var user = Model.User.findOne({
    where : {
      username: lusername
    }
  }).catch(function(rerror){
    res.json({status:-1, error : rerror})
  });

  res.json({status:0, user:user})
}

module.exports.getNearPlayersRanked = function(req, res){
  res.send('test');
}

/**
 * The master create game
 */
module.exports.createGame = function(req, res){
  var winner = Model.Team.build({
    score: 0
  })

  var looser = Model.Team.build({
    score: 0
  })

  winner.save().catch(function (error){
    res.json({status:-1, error : error})
  })
  looser.save().catch(function (error){
    res.json({status:-1, error : error})
  })

  var game = Model.Game.build({
    time: req.body.gameTime,
    ltid: looser.tid,
    wtid: winner.tid
  }).save().then(function (game){

    res.json({status:0, game:game})
  }).catch(function (error){
    res.json({status:-1, error : error})
  })
}
/**
 * And an user game
 * params game : time
 */
module.exports.addUserGame = function(req, res){
  req.body.gameStats = {winningTeamId,LosingTeamId}


  res.send('test');
}

module.exports.getGamesListByUID = function(req, res){
  res.send('test')
}

module.exports.getTeamsListByUID = function(req, res){
  res.send('etss')
}
