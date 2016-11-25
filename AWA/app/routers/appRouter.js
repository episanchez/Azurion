var passport = require('passport'),
    signupController = require('../controllers/signupController.js')

module.exports = function(express) {
  var router = express.Router()

  var isAuthenticated = function (req, res, next) {
    if (req.isAuthenticated())
      return next()
    req.flash('error', 'You have to be logged in to access the page.')
    res.redirect('/admin/')
  }

  router.get('/admin/signup', signupController.show)
  router.post('/admin/signup', signupController.signup)

  router.post('/admin/login', passport.authenticate('local', {
      successRedirect: '/admin/dashboard',
      failureRedirect: '/admin/',
      failureFlash: true
  }))

  router.get('/admin', function(req, res) {
    res.render('home')
  })

  router.get('/admin/dashboard', isAuthenticated, function(req, res) {
    res.render('dashboard')
  })

  router.get('/admin/logout', function(req, res) {
    req.logout()
    res.redirect('/admin/')
  })


  router.get('/api/login', passport.authenticate('basic', {
    session: false
  }), function (req, res){
    res.json({username: req.user});
  });

  return router
}
