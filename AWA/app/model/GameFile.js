var Sequelize = require('sequelize');

var attributes = {
  filename :{
    type: Sequelize.STRING,
    allowNull: false,
    unique: true
  },

  url : {
    type : Sequelize.STRING,
    allowNull : false
  },
  
  size : {
    type: Sequelize.INTEGER
  },

  hash: {
    type : Sequelize.TEXT
  }
}

var options = {
  freezeTableName: true
}

module.exports.attributes = attributes
module.exports.options = options
