// npm i axios
const axios = require('axios');

let payload = {
    "username": "john",
    "password": "john"
}

axios.post('http://localhost:8080/login', payload)
    .then(res => {
            axios.get('http://localhost:8080/tasks', {headers: {Authorization: res.headers.authorization}})
                .then(res => {
                    console.log(res.data)
                })

            let task = {
                "description": "Buy some milk"
            }

            axios.post('http://localhost:8080/tasks', task, {headers: {Authorization: res.headers.authorization}})
                .then(res => {
                    console.log(res.data)
                })

            axios.get('http://localhost:8080/tasks', {headers: {Authorization: res.headers.authorization}})
                .then(res => {
                    console.log(res.data)
                })
        }
    );
