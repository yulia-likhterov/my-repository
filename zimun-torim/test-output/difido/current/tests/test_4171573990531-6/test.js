var test = {"uid":"4171573990531-6","reportElements":[{"title":"Step 1 - Fill services from file to expectedServices list","message":null,"status":"success","type":"startLevel","time":"13:36:54.556"},{"title":null,"message":null,"status":"success","type":"stopLevel","time":"13:36:54.573"},{"title":"Step 2 - Fill id and date of birth values from file","message":null,"status":"success","type":"startLevel","time":"13:36:54.573"},{"title":"Browse to URL: https://my.health.gov.il/MyPortal/Pages/FHCAppointWOP.aspx","message":null,"status":"success","type":"regular","time":"13:36:54.573"},{"title":"*** Current page: ZimunTorimPersonalDetailsPage","message":null,"status":"success","type":"regular","time":"13:36:56.456"},{"title":"Write '999846843' to: 'Id' input (By.id: personalId)","message":null,"status":"success","type":"regular","time":"13:36:56.467"},{"title":"Write '02' to: 'dobDate' input (By.cssSelector: input[type='text'][tabindex='2'])","message":null,"status":"success","type":"regular","time":"13:36:56.838"},{"title":"Write '07' to: 'dobMonth' input (By.cssSelector: input[type='text'][tabindex='3'])","message":null,"status":"success","type":"regular","time":"13:36:57.181"},{"title":"Write '1991' to: 'dobYear' input (By.cssSelector: input[type='text'][tabindex='4'])","message":null,"status":"success","type":"regular","time":"13:36:57.504"},{"title":null,"message":null,"status":"success","type":"stopLevel","time":"13:36:58.036"},{"title":"Step 3 - Get the available values in the requested service select","message":null,"status":"success","type":"startLevel","time":"13:36:58.047"},{"title":"*** Current page: ZimunTorimPersonalDetailsPage","message":null,"status":"success","type":"regular","time":"13:36:58.047"},{"title":"Click: 'selectService' dropdown (By.cssSelector: div[class='ui-select-match'])","message":null,"status":"success","type":"regular","time":"13:36:58.047"},{"title":"Click: 'selectAServiceFrom' dropdown (By.cssSelector: span[class='ui-select-placeholder text-muted ng-binding'])","message":null,"status":"success","type":"regular","time":"13:36:58.251"},{"title":"Element 'optionalService' label (By.id: ui-select-choices-row-0-0) inner text: 'ביקור ראשון בטיפת חלב'","message":null,"status":"success","type":"regular","time":"13:36:58.568"},{"title":"Element 'optionalService' label (By.id: ui-select-choices-row-0-1) inner text: ''","message":null,"status":"success","type":"regular","time":"13:37:50.913"},{"title":"Screen shot of available services per patient type","message":null,"status":"success","type":"regular","time":"13:37:51.272"},{"title":"Screen shot of available services per patient type","message":"screenshot11915897089449793075.png","status":"success","type":"img","time":"13:37:51.285"},{"title":null,"message":null,"status":"success","type":"stopLevel","time":"13:37:51.285"},{"title":"Step 4 - Compare between 2 lists, and verify that the actual available values of the select, are identical to the expected values received from the csv file","message":null,"status":"success","type":"startLevel","time":"13:37:51.285"},{"title":"Expecting to see 'ביקור ראשון בטיפת חלב' within the options of service select - OK","message":null,"status":"success","type":"regular","time":"13:37:51.285"},{"title":null,"message":null,"status":"success","type":"stopLevel","time":"13:37:51.285"}]};