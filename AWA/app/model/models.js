/*
 * Implement Meta Models and make Models Association
 * @author Charlie QUILLARD
 */
var UserMeta = require('./User.js'),
    GameMeta = require('./Game.js'),
    TeamMeta = require('./Team.js'),
    UsergameMeta = require('./Usergame.js'),
    connection = require('../sequelize.js')


var User = connection.define('users', UserMeta.attributes, UserMeta.options)
var Game = connection.define('games', GameMeta.attributes, GameMeta.options)
var Team = connection.define('teams', TeamMeta.attributes, TeamMeta.options)
var Usergame = connection.define('usergame', UsergameMeta.attributes, UsergameMeta.options)

User.hasMany(Usergame, {as: 'usergames'})
Game.hasMany(User, {as: 'Players'})

/** Include wtid and ltid into Game tables **/
Team.hasOne(Game, {as: 'WinningTeam', foreignKey: 'wtid'})
Team.hasOne(Game, {as: 'LosingTeam', foreignKey: 'ltid'})
Game.belongsTo(Team)

Usergame.belongsTo(User)
Usergame.belongsTo(Team)

Team.hasMany(Usergame, {as: 'players'})

//Applying Item Table to database
connection.sync({force: true})

module.exports.User = User
