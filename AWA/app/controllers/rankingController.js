/**
 * Controller Of API
 * @since 0.2
 */

var Model = require('../model/models')
var Sequelize = require('../sequelize')
/**
 * Get Player
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
  var un = Model.Usergame.findOne({
    rank: {
      $eq:1
    }
  }).then (function (result){
      console.log(un['rank']);
    res.json({ranking:result})
  }).catch(function (error){
      res.send('test');
  })

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

  // Implement HomeTeam And AwayTeam
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

  Model.Usergame.create({
    reputation: req.body.reputation,
    userUid: req.body.uid,
    flags: req.body.flags,
    teamTid: req.body.tid
  }).then(function (usergame){
    res.json({status:0, usergame:usergame})
  }).catch(function (error){
    res.json({status:-1, error : error})
  })
}

module.exports.getGamesListByUID = function(req, res){
req.body.uid="fd120a8c-5a8f-4fb3-bb0f-08f83642a6fd"
  Sequelize.query('SELECT * FROM usergame WHERE userUid = ?',
  { replacements: [req.body.uid], type: Sequelize.QueryTypes.SELECT}
).then(function (usergames){
  res.json({status:0, usergames:usergames})
}).catch(function (error){
  res.json({status:-1, error : error})
})
}

module.exports.getTeamsListByUID = function(req, res){
  req.body.uid="fd120a8c-5a8f-4fb3-bb0f-08f83642a6fd"
  Sequelize.query('SELECT * FROM teams t JOIN usergame ug ON ug.teamTid=t.tid WHERE ug.userUid = ?',
  { replacements: [req.body.uid], type: Sequelize.QueryTypes.SELECT}
).then(function (usergames){
  res.json({status:0, usergames:usergames})
}).catch(function (error){
  res.json({status:-1, error : error})
})
}
