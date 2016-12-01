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

module.exports.addUserGame = function(req, res){
  res.send('test');
}

module.exports.getGamesListByUID = function(req, res){
  res.send('test')
}

module.exports.getTeamsListByUID = function(req, res){
  res.send('etss')
}
