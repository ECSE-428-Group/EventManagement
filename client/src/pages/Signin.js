import React, { useState } from 'react';

// Router
import { useNavigate } from 'react-router-dom';

// Components
import LandingPageAuth from '../components/LandingPageAuth';

const INITIAL_FORM_DATA = {
    username: '',
    password: '',
};

export default function Signin() {
    // const baseURL = 'https://event-management-app-backend.herokuapp.com/';
    const baseURLTesting = 'http://localhost:8080/';
    const navigate = useNavigate();

    const [accountData, setAccountData] = useState();

    const textfieldNames = ['username', 'password'];

    const handleForm = (e) => {
        setAccountData({
            ...accountData, // The triple dot is called spread operator and allows an iterable like an array to expand all it's elements
            [e.currentTarget.id]: e.currentTarget.value,
        });
    };

    function checkData(data) {
        if (data === true) {
            navigate('/userhome'); //CHANGE THIS TO LANDING PAGE OF APP
        } else {
            setAccountData(INITIAL_FORM_DATA);
            window.location.reload(false);
        }
    }

    const handleOnClickSignIn = () => {
        localStorage.setItem('username', accountData.username);
        localStorage.setItem('password', accountData.password);

        if (accountData.username == '' || accountData.password == '') {
            return; // Can also do alert
        }

        fetch(
            baseURLTesting +
                'users/checkUser/' +
                accountData.username +
                '?password=' +
                accountData.password,
            {
                method: 'GET',
                mode: 'cors',
                headers: {
                    'Content-Type': 'application/json',
                },
            }
        )
            .then((response) => response.json())
            .then((data) => checkData(data));

        // handleCreateAccount(accountData);
        setAccountData(INITIAL_FORM_DATA);
    };

    const input = {
        data: ['Username', 'Password'],
        label: ['Username', 'Password'],
        page: '/signup',
        type: 'Sign In',
        button: 'Login',
        footer: "Don't have an account?",
        span: 'Join Us!',
    };
    return (
        <LandingPageAuth
            textfieldNames={textfieldNames}
            input={input}
            handleOnClick={handleOnClickSignIn}
            handleForm={handleForm}
            formErrors={undefined}
        />
    );
}
