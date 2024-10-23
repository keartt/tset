 $.ajax({
        type: 'GET',
        url: 'test/selectAll.do',
        dataType: 'json',
        contentType: "application/json; charset=UTF-8",
        success: function(res) {
          console.log('서버 응답:', res.testdb);
        }
      });

function submitForm() {
      var name = $('#name').val();
      var phoneNumber = $('#phoneNumber').val();
      var testVo = {
    		  name : $('#name').val(),
    		  phoneNumber : $('#phoneNumber').val()
      }
      
      $.ajax({
        type: 'POST',  
        url: 'test/insert.do',  
        dataType: 'json',  
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify(testVo),  // JSON 형식으로 데이터 전송
        success: function(res) {
          console.log('서버 응답:', res.page);
           window.location.href = res.page;
        }
      });
    }
