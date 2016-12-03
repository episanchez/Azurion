/**
 * Team Meta Models
 * @author episanchez
*/

var Sequelize = require('sequelize')

var attributes = {
  tid:{ // TEAM ID
    primaryKey:true,
    type: Sequelize.UUID,
    allowNull: false,
    unique: true,
    defaultValue: Sequelize.UUIDV4
  },
  score: {
    type: Sequelize.INTEGER
  }
}

var options = {
  freezeTableName: true
}

module.exports.attributes = attributes;
module.exports.options = options;
