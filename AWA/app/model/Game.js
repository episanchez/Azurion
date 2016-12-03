/**
 * Model Of Player's Game
 * @author Charlie QUILLARD
 */
var Sequelize = require('sequelize')

var attributes = {
  gid:{ // Game ID
    primaryKey: true,
    type : Sequelize.UUID,
    allowNull: false,
    unique: true,
    defaultValue: Sequelize.UUIDV4
  },
  date:{
    type: Sequelize.DATE
  },
  time:{
    type: Sequelize.BIGINT
  }

}

var options = {
  freezeTableName: true
}

module.exports.attributes = attributes;
module.exports.options = options;
