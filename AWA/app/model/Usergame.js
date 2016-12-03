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
  kills:{
    type: Sequelize.INTEGER
  },
  killed:{
    type: Sequelize.INTEGER
  },
  flags:{
    type: Sequelize.INTEGER
  }
}

var options = {
  freezeTableName: true
}

module.exports.attributes = attributes;
module.exports.options = options;
