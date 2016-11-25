using UnityEngine;
using Prototype.NetworkLobby;
using System.Collections;
using UnityEngine.Networking;

public class NetworkLobbyHook : LobbyHook 
{
	public override void OnLobbyServerSceneLoadedForPlayer(NetworkManager manager, GameObject lobbyPlayer, GameObject gamePlayer)
	{
		LobbyPlayer lobby = lobbyPlayer.GetComponent<LobbyPlayer>();
		characterhelper player = gamePlayer.GetComponent<characterhelper>();
		player.color = lobby.playerColor;
		player.name = lobby.playerName;

	}
}
