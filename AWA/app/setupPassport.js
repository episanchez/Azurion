var passport = require('passport'),
    LocalStrategy = require('passport-local').Strategy,
    BasicStrategy = require('passport-http').BasicStrategy,
    bcrypt = require('bcrypt'),
    Model = require('./model/models.js')

function dbAuthentication(username, password, done){
  console.log("autten");
  Model.User.findOne({
    where: {
      'username': username
    }
  }).then(function (user) {
    if (user == null) {
      return done(null, false, { message: 'Incorrect credentials.' })
    }

    var hashedPassword = bcrypt.hashSync(password, user.salt)

    if (user.password === hashedPassword) {
      return done(null, user)
    }

    return done(null, false, { message: 'Incorrect credentials.' })
  })
}
module.exports = function(app) {
  app.use(passport.initialize())
  app.use(passport.session())

  passport.use(new LocalStrategy(dbAuthentication));

  passport.use(new BasicStrategy(dbAuthentication));

  passport.serializeUser(function(user, done) {
    done(null, user.id)
  })

  passport.deserializeUser(function(id, done) {
    Model.User.findOne({
      where: {
        'id': id
      }
    }).then(function (user) {
      if (user == null) {
        done(new Error('Wrong user id.'))
      }

      done(null, user)
    })
  })
}
