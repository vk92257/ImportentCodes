   
   
   
   // code for sorting the keys of the hasMap by date
   
      private fun sortingCallLogs(callLogs: HashMap<String, HashMap<String, ArrayList<ContactInfo>>>): TreeMap<String, HashMap<String, ArrayList<ContactInfo>>> {
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)
        val comparator =
            Comparator<String> { s1, s2 ->
                Log.e("TAG", "sortingCallLogs:  $s1   and $s2" )
                LocalDate.parse(s1, formatter).compareTo(LocalDate.parse(s2, formatter))
            }
        val sorted =  TreeMap<String,HashMap<String, ArrayList<ContactInfo>>>(comparator)
            sorted.putAll(callLogs)
        return sorted
    }
    
    
    
    // today or yester by comparing the dates
    
    
      @SuppressLint("SimpleDateFormat")
    fun isTodayOrYesterday(date: String , pattern:String): String {
        try {
            val datFormat = SimpleDateFormat(pattern)
            val dateToCompare: Date? = datFormat.parse(date)
            val cal = Calendar.getInstance()
            val oldCal = Calendar.getInstance()
            oldCal.time = dateToCompare
            val oldYear = oldCal[Calendar.YEAR]
            val year = cal[Calendar.YEAR]
            val oldDay = oldCal[Calendar.DAY_OF_YEAR]
            val day = cal[Calendar.DAY_OF_YEAR]

            if (oldYear == year) {
                val value = oldDay - day
                when (value) {
                    -1 -> return "yesterday"
                    0 -> return "today"
                    1 -> return "tomorrow"
                    else -> return date
                }
            } else {
                return date
            }
