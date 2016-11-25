var Sequelize = require('sequelize');

var attributes = {
  versionNumber:{
    type: Sequelize.STRING,
    allowNull: false,
    unique: true
  },
  productionType : {
    type: Sequelize.ENUM('Release', 'PreRelease', 'Beta', 'Alpha')
  },

  VersionningLevel : {
    type: Sequelize.ENUM('MAJOR', 'MINOR', 'PATCH')
  },

  description: {
    type : Sequelize.STRING
  }
}

var options = {
  freezeTableName: true
}

module.exports.attributes = attributes;
module.exports.options = options;
