function date_time(id)
{
        date = new Date;
        year = date.getFullYear();
        month = date.getMonth();
        months = new Array('Stycznia', 'Lutego', 'Marca', 'Kwietnia', 'Maja', 'Czerwca', 'Lipca', 'Sierpnia', 'Września', 'Października', 'Listopada', 'Grudnia');
        d = date.getDate();
        day = date.getDay();
        days = new Array('Niedziela', 'Poniedziałek', 'Wtorek', 'Środa', 'Czwartek', 'Piątek', 'Sobota');
        h = date.getHours();
        if(h<10)
        {
                h = "0"+h;
        }
        m = date.getMinutes();
        if(m<10)
        {
                m = "0"+m;
        }
        s = date.getSeconds();
        if(s<10)
        {
                s = "0"+s;
        }
          
		onejan = new Date(date.getFullYear(), 0, 1);
		w = Math.ceil( (((date - onejan) / 86400000) + onejan.getDay() + 1) / 7 );
        
		result = '<span class="time">&nbsp;'+h+':'+m+':'+s+'&nbsp;</span>  <span class="week">&nbsp;'+'W'+w+' &nbsp;</span> <span class="date">&nbsp;'+days[day]+' '+d+' '+months[month]+' '+year+' r.&nbsp;</span>';
        document.getElementById(id).innerHTML = result;
        setTimeout('date_time("'+id+'");','1000');
        return true;
}