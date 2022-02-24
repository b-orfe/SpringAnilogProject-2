
const delete_message ="削除してもよろしいですか？"
$('.delete-action').click(function(){
	if(!confirm(delete_message)){
	return false;
	}

});

const comp_message ="完了してもよろしいですか？"
$('.comp-action').click(function(){
	if(!confirm(comp_message)){
	return false;
	}

});
