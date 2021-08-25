var stompClient = null

function connect() {
    let socket = new SockJS("/server1"); //we get the socket object by fire /server1
    //provide socket object to stompClient because stompSocket provide message sending/receiving features
    stompClient = Stomp.over(socket); //i.e stomp will run over our socket

    //to connect with our server (springboot)
    stompClient.connect({}, function (frame) {
        console.log("connected :- " + frame);

        $("#name-form").addClass("d-none") //hide form
        $("#chat-room").removeClass("d-none") //show chat room

        //subscribe url
        stompClient.subscribe("/topic/return-to", (respone) => {
            showMessage(JSON.parse(respone.body));
        })

    })

}

function showMessage(message) {
    $("#message-container-table").prepend(`
     <tr>
       <td>
         <b>${message.name} :</b>${message.content}
       </td>
     </tr>
    `)
}

function sendMessage(){
    let jspnObj={
        name:localStorage.getItem("name"),
        content:$("#message-value").val()
    }

    stompClient.send("/app/message",{},JSON.stringify(jspnObj));
}


//main function :- flow start from here
$(document).ready(e => {
        $("#login").click(
            () => {
                let name = $("#name-value").val()
                localStorage.setItem("name", name)
                $("name-title").html(`Welcome , <b>${name}</b>`)
                connect();
            }
        )

        $('#send-btn').click(
            ()=>{
                sendMessage()
            }
        )

        $('#logout').click(
            ()=>{
                localStorage.removeItem("name")
                if(stompClient!=null) {
                    stompClient.disconnect()
                    $("#name-form").removeClass("d-none") //hide chat room
                    $("#chat-room").addClass("d-none") //show form
                }
            }
        )
    }
)