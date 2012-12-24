package com.tarena.entity;

import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Comparator;
import java.util.Locale;

public class SortBySinger implements Comparator<Music> {
	RuleBasedCollator c = (RuleBasedCollator) Collator
			.getInstance(Locale.CHINA);

	@Override
	public int compare(Music m1, Music m2) {
		// TODO Auto-generated method stub
		return c.compare(m1, m2);
	}

}
