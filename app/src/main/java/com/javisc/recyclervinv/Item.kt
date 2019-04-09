package com.javisc.recyclervinv

data class Item(val day: Int, val subItemList: List<SubItem>)

data class SubItem(val hourStart: Int, val hourEnd: Int)