package com.unknown.CAMP;

import java.util.ArrayList;

public class Manager {

	private ArrayList<Camper> fullRoster;
	private ArrayList<ArrayList<Camper>> cabinRoster = new ArrayList<ArrayList<Camper>>(0);
	private ArrayList<ArrayList<Camper>> femaleChunks = new ArrayList<ArrayList<Camper>>(0);
	private ArrayList<ArrayList<Camper>> maleChunks = new ArrayList<ArrayList<Camper>>(0);
	private int maleCabins, femaleCabins, totalCampers;

	public Manager(ArrayList<Camper> a) {
		fullRoster = a;
		totalCampers = fullRoster.size();
		maleCabins = 4;
		femaleCabins = 4;
	}

	public Manager(ArrayList<Camper> a, int mcabins, int fcabins) {
		fullRoster = a;
		maleCabins = mcabins;
		femaleCabins = fcabins;
		totalCampers = fullRoster.size();
	}

	public int chunkMaker() {
		int count = 0;
		ArrayList<Camper> temp = new ArrayList<Camper>(0);
		if (fullRoster.size() < 1) {
			System.out.println(maleChunks);
			System.out.println(femaleChunks);
			return 1;
		}
		temp.add(fullRoster.remove(0));
		while (count < temp.size()) {
			for (int a = 0; a < fullRoster.size(); a++) {
				if (fullRoster.get(a).getRequest().equals(temp.get(count).getName())
						&& fullRoster.get(a).getGender() == temp.get(count).getGender()) {
					temp.add(fullRoster.remove(a));
					a--;
				}
			}
			for (int a = 0; a < fullRoster.size(); a++) {
				if (fullRoster.get(a).getName().equals(temp.get(count).getRequest())
						&& fullRoster.get(a).getGender() == temp.get(count).getGender()) {
					temp.add(fullRoster.remove(a));
					a--;
				}
			}
			count++;
		}
		if (temp.get(0).getGender() == 1) {
			maleChunks.add(temp);
			chunkMaker();
		} else if (temp.get(0).getGender() == 2) {
			femaleChunks.add(temp);
			chunkMaker();
		}

		return -1;
	}

	public void cabinAssigner(){
		
	}
	public static void main(String[] args) {
		// Creates an ArrayList of campers.
		// It will be a for loop soon, but i'm still unsure on the input.
		ArrayList<Camper> campers = new ArrayList<Camper>(0);
		
		campers.add(new Camper("b", "a", 1, 8));
		campers.add(new Camper("a", "c", 1, 8));
		campers.add(new Camper("c", "a", 1, 8));
		campers.add(new Camper("d", "s", 1, 8));
		campers.add(new Camper("e", "f", 1, 8));
		campers.add(new Camper("g", "h", 1, 8));
		campers.add(new Camper("h", "b", 1, 8));
		campers.add(new Camper("j", "c", 1, 8));
		campers.add(new Camper("k", "a", 1, 8));
		campers.add(new Camper("l", "c", 1, 8));

		Manager addie = new Manager(campers);
		addie.chunkMaker();
	}

}

