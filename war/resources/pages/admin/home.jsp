<script>
$("#jqGrid01").jqGrid({
    data: mydata,
    datatype: "local",
    height: 250,
    rowNum: 10,
    rowList: [10,20,30],
    colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'],
    colModel:[
        {name:'id',index:'id', width:60, sorttype:"int",search:true},
        {name:'invdate',index:'invdate', width:90, sorttype:"date", formatter:"date"},
        {name:'name',index:'name', width:100},
        {name:'amount',index:'amount', width:80, align:"right",sorttype:"float", formatter:"number"},
        {name:'tax',index:'tax', width:80, align:"right",sorttype:"float"},        
        {name:'total',index:'total', width:80,align:"right",sorttype:"float"},        
        {name:'note',index:'note', width:150, sortable:false}        
    ],
    pager: "#jqGridPager01",
    viewrecords: true,
    caption: "Sample jqGrid Table",
    hidegrid:false,
    altRows: true                
});
$("#jqGrid01")
.jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
.jqGrid('setSelection', '3');

</script>

<div id="jqGrid01"></div>