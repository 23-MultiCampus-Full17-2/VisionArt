// CreatePost.js

  function setArtFieldId() {
        var selectedArtField = document.getElementById("selectedArtField").value;
        console.log("Selected Art Field: " + selectedArtField);
        var artFieldIdInput = document.getElementById("artFieldId");
        
        // 선택된 드롭다운 값에 따라 hidden 필드의 값을 설정함.
        switch (selectedArtField) {
            
            case "유머글":
                artFieldIdInput.value = 10;
                break;
            case "자랑하기":
                artFieldIdInput.value = 11;
                break;
            case "질문하기":
                artFieldIdInput.value = 12;
                break;
            case "정보공유":
                artFieldIdInput.value = 13;
                break;
            case "그냥":
                artFieldIdInput.value = 14;
                break; 
            case "카테고리선택하기":
                artFieldIdInput.value = 15;
                break; 
            // 다른 옵션에 대한 경우 추가
            default:
                artFieldIdInput.value = 14; // 기본값 설정
        }
        console.log("Art Field ID: " + artFieldIdInput.value);
    }
