using UnityEngine;
using UnityEngine.UI;
using System.Collections;

namespace Prototype.NetworkLobby
{
    //Main menu, mainly only a bunch of callback called by the UI (setup throught the Inspector)
    public class LobbyMainMenu : MonoBehaviour 
    {
        public LobbyManager lobbyManager;
		public Text tuto1;
		public Text tuto2;
        public RectTransform lobbyServerList;
        public RectTransform lobbyPanel;

        public InputField ipInput;
        public InputField matchNameInput;

        public void OnEnable()
        {
            lobbyManager.topPanel.ToggleVisibility(true);

//            ipInput.onEndEdit.RemoveAllListeners();
//            ipInput.onEndEdit.AddListener(onEndEditIP);

            matchNameInput.onEndEdit.RemoveAllListeners();
            matchNameInput.onEndEdit.AddListener(onEndEditGameName);
        }

        public void OnClickHost()
        {
            lobbyManager.StartHost();
        }

        public void OnClickJoin()
        {
            lobbyManager.ChangeTo(lobbyPanel);

            lobbyManager.networkAddress = ipInput.text;
            lobbyManager.StartClient();

            lobbyManager.backDelegate = lobbyManager.StopClientClbk;
            lobbyManager.DisplayIsConnecting();

            lobbyManager.SetServerInfo("Creation...", lobbyManager.networkAddress);
        }

        public void OnClickDedicated()
        {
            lobbyManager.ChangeTo(null);
            lobbyManager.StartServer();

            lobbyManager.backDelegate = lobbyManager.StopServerClbk;

            lobbyManager.SetServerInfo("Dedicated Server", lobbyManager.networkAddress);
        }

        public void OnClickCreateMatchmakingGame()
        {
            lobbyManager.StartMatchMaker();
            lobbyManager.matchMaker.CreateMatch(
                matchNameInput.text,
                (uint)lobbyManager.maxPlayers,
                true,
                "", "","", 0, 0,
                lobbyManager.OnMatchCreate);

            lobbyManager.backDelegate = lobbyManager.StopHost;
            lobbyManager._isMatchmaking = true;
            lobbyManager.DisplayIsConnecting();

            lobbyManager.SetServerInfo("Matchmaker Host", lobbyManager.matchHost);
        }

		public void OnClickOptions()
		{
			Debug.Log ("Options");
		}

		public void OnClickTutorial()
		{
			lobbyManager.mainMenuPanel.gameObject.SetActive (false);
			tuto1.gameObject.SetActive (true);
			tuto2.gameObject.SetActive(true);
		}
		public void OnClickRetour()
		{
			lobbyManager.mainMenuPanel.gameObject.SetActive (true);
			tuto1.gameObject.SetActive (false);
			tuto2.gameObject.SetActive(false);
		}
		public void OnClickExit()
		{
			Application.Quit();
		}

        public void OnClickOpenServerList()
        {
            lobbyManager.StartMatchMaker();
            lobbyManager.backDelegate = lobbyManager.SimpleBackClbk;
            lobbyManager.ChangeTo(lobbyServerList);
        }

        void onEndEditIP(string text)
        {
            if (Input.GetKeyDown(KeyCode.Return))
            {
                OnClickJoin();
            }
        }

        void onEndEditGameName(string text)
        {
            if (Input.GetKeyDown(KeyCode.Return))
            {
                OnClickCreateMatchmakingGame();
            }
        }

    }
}
