/*
 * The route of API
 * @since 0.2
 */

var passport = require('passport')
var RankingController = require('../controllers/rankingController')

module.exports = function(express){
  var router = express.Router()

  /**
   * The First Version Of API RANKING
   * @since 0.2
   */
  var SVers = '/0.2/';
  router.get(SVers +'getPlayerRank', passport.authenticate("basic", { session: false }), RankingController.getPlayerRank)

  router.get(SVers + 'getNearPlayersRanked', passport.authenticate("basic", { session: false }), RankingController.getNearPlayersRanked)

  router.post(SVers + 'addUserGame', passport.authenticate("basic", {session : false}), RankingController.addUserGame)

  router.post(SVers + 'createGame', passport.authenticate("basic", {session : false}), RankingController.createGame)

  router.get(SVers + 'getGamesListByUID', passport.authenticate("basic", {session: false}), RankingController.getGamesListByUID)

  router.get(SVers + 'getTeamsListByUID', passport.authenticate("basic", {session: false}), RankingController.getTeamsListByUID)

  return router;
}
