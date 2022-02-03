require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Начнём. Ты находишься в основном сценарии Context-Switch 

    state: Hello
        intent!: /привет
        a: Привет привет



    state: SwitchToReminder
        q!: * переключись на напомина* *
        script:
            $response.replies = $response.replies || []; // Инициализация массива `$response.replies`
            $response.replies.push({
                type: "context-switch",
                targetBotId: "1000009678-yogabot_v0_3-1000009678-tpi-20938101695",
                targetState: "/Start",
                parameters: {}
            });
            
    state: SwitchToWeather
        q!: * переключись на погоду *
        script:
            $response.replies = $response.replies || []; // Инициализация массива `$response.replies`
            $response.replies.push({
                type: "context-switch",
                targetBotId: "1000009678-_demo_bot_dlya_progn-1000009678-tKm-20938050406",
###             взять во внимание название стейта в боте^   
                targetState: "/Welcome",
                parameters: {}
            });        
    
    
    state: SwitchToMain
        q!: * переключись обратно *
        script:
            $response.replies = $response.replies || []; // Инициализация массива `$response.replies`
            $response.replies.push({
                type: "context-return",
                state: "/Start",
                data: {}
            });


    state: Bye
        intent!: /пока
        a: Пока пока

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}