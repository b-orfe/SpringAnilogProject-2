
const delete_message ="削除してもよろしいですか？"
$('.delete-action').click(function(){
	if(!confirm(delete_message)){
	return false;
	}

});