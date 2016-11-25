using UnityEngine;
using System.Collections;

public class Damage : Health {
	public GameObject mysword;

	// Use this for initialization
	void Start () {

	}
	void OnCollisionEnter(Collision collision) {
		if (collision.gameObject != mysword && collision.gameObject.name == "sword") {
			this.TakeDamage (15);
		}
	}
	// Update is called once per frame
	void Update () {
	
	}
}
