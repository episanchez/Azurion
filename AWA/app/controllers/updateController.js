
var Model = require('../model/models.js')
var VersionCompare = require('../util/versionCompare.js');

module.exports.getFilesToUpdate = function (req, res){
  var playerVersion = req.body.playerVersion;
  var playerOS = req.body.playerOS;
  var playerArch = req.body.playerArch;

  var filesURL = [];

  if (!playerVersion || !playerArch || !playerOS) {
    res.json({error: true, errorMsg: "Problem with params"})
  }

  var lastVersion = Model.GameVersion.max('id');
  if (VersionCompare(playerVersion, lastVersion.getVersionNumber()) < 0){
    // check lastest update

    Model.GameFile.findAll().then(gameFiles){
      foreach (gameFile in gameFiles){
        if (VersionCompare(playerVersion, gameFile.getGameVersionFile().getVersionNumber()) < 0){
          filesURL.push(gameFile.getUrl());
        }
      }
    }

    res.json({error: false, files : filesURL});
  }
  res.json({error: true, errorMsg : "Game is already up-date"});
}

module.exports.createUpdate = function (req, res){

}
