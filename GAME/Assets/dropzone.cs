using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using UnityEngine.Networking;

public class dropzone : NetworkBehaviour {
	public GameObject dropcanvas;
	public Text zonetxt;
	public RectTransform zonebar;

	public int redplayers = 0;
	public int blueplayers = 0;
	private float startTimer;

	// Use this for initialization
	void Start () {
	}

	void OnChangeTime(float time)
	{
		Color winningcolor = Color.black;

		if (redplayers > 0)
			winningcolor = Color.red;
		else if (blueplayers > 0)
			winningcolor = Color.blue;
		if (time <= 15)
			zonebar.sizeDelta = new Vector2 (time * 10, zonebar.sizeDelta.y);
		else {
			Debug.Log("zone capturee");
			foreach(GameObject fooObj in GameObject.FindGameObjectsWithTag("Player"))
			{
				fooObj.SendMessage("displayend", winningcolor);
			}
		}
	}

	void ChangeZoneAttribute()
	{
		dropcanvas.gameObject.SetActive (true);
		if (blueplayers > 0 && redplayers == 0)
			zonetxt.text = "L'equipe bleu capture le chateau !";
		else if (blueplayers == 0 && redplayers > 0)
			zonetxt.text = "L'equipe rouge capture le chateau !";
		else if (blueplayers > 0 && redplayers > 0)
			zonetxt.text = "Des ennemis se trouvent encore dans le chateau !";
	}

	void OnTriggerEnter(Collider other)
	{
		if (other.gameObject.GetComponent<characterhelper> ().color == Color.blue) {
			blueplayers = blueplayers + 1;
		}
		if (other.gameObject.GetComponent<characterhelper> ().color == Color.red) {
			redplayers = redplayers + 1;
		}
		if ((blueplayers > 0 && redplayers == 0) || (blueplayers == 0 && redplayers > 0)) {
			startTimer = Time.time;
		}
		ChangeZoneAttribute ();
	}

	void OnTriggerStay(Collider other)
	{
		if ((blueplayers > 0 && redplayers == 0) || (blueplayers == 0 && redplayers > 0)) {
			float time = (Time.time - startTimer) % 60;
			OnChangeTime (time);
		} 
		else if (blueplayers > 0 && redplayers > 0) {
			startTimer = Time.time;
		}
	}

	void OnTriggerExit(Collider other)
	{
		dropcanvas.gameObject.SetActive (false);

		if (other.gameObject.GetComponent<characterhelper> ().color == Color.blue) {
			Debug.Log ("un joueur bleu quitte dans la zone");
			blueplayers = blueplayers - 1;
		}
		if (other.gameObject.GetComponent<characterhelper> ().color == Color.red) {
			Debug.Log ("un joueur rouge quitte dans la zone");
			redplayers = redplayers - 1;
		}
	}

	// Update is called once per frame
	void Update () {

	}
}
