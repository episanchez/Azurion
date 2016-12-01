/**
 * Implement Meta Models and make Models Association
 * @author Charlie QUILLARD
 */
var UserMeta = require('./User.js'),
    GameMeta = require('./Game.js'),
    connection = require('../sequelize.js')


var User = connection.define('users', UserMeta.attributes, UserMeta.options)
var Game = connection.define('games', GameMeta.attributes, GameMeta.options)
//var GameVersion = connection.define('gameVersions', GameVersionMeta.attributes, GameVersionMeta.options);
//var GameFile = connection.define('gameFiles', GameFileMeta.attributes, GameFileMeta.options);

//GameVersion.hasMany(GameFile, {as: 'GameVersionFile'});
// you can define relationships here

//Applying Item Table to database
connection.sync({force: true})

module.exports.User = User
