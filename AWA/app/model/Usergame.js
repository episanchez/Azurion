/**
 * UserGame Meta Models
 * @author episanchez
 */

var Sequelize = require('sequelize')

var attributes = {
  ugid:{
    primaryKey: true,
    type : Sequelize.UUID,
    allowNull: false,
    unique: true,
    defaultValue: Sequelize.UUIDV4
  },
  reputation: {
    type: Sequelize.INTEGER
  },
  flags:{
    type: Sequelize.TEXT // JSON
  }
}

var options = {
  freezeTableName: true
}

module.exports.attributes = attributes;
module.exports.options = options;
