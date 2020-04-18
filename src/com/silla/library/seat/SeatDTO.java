package com.silla.library.seat;

import java.sql.Timestamp;

public class SeatDTO {
	private int rno;
	private int sno;
	private int mno;
	private int ano;
	private boolean seatcheck;
	private Timestamp starttime;
	private Timestamp endtime;

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public boolean isSeatcheck() {
		return seatcheck;
	}

	public void setSeatcheck(boolean seatcheck) {
		this.seatcheck = seatcheck;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public Timestamp getStarttime() {
		return starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	


}