var Sequelize = require('sequelize'),
    sequelize = new Sequelize('azurion', 'root', 'y{3qbTD/', {
                              host: "localhost",
                              port: 3306,
                              dialect: 'mysql'
                });

module.exports = sequelize
