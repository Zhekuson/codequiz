package com.nuclearfoxes.codequiz.ui.stats.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nuclearfoxes.codequiz.ui.stats.StatsPartFragment

class StatsPartFragmentAdapter(fm:FragmentManager)
    :FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return StatsPartFragment(position)
    }

    override fun getCount(): Int {
        return 3
    }
}