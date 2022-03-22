import React, { useState } from 'react';

// Components
import LandingPageAuth from '../components/LandingPageAuth';

const INITIAL_FORM_DATA = {
    username: '',
    password: '',
    firstname: '',
    lastname: '',
    birthday: '',
    email: '',
};

function Signup({ handleCreateAccount }) {
    const [accountData, setAccountData] = useState();

    const textfieldNames = [
        'username',
        'password',
        'firstname',
        'lastname',
        'birthday',
        'email',
    ];

    const handleForm = (e) => {
        setAccountData({
            ...accountData, // The triple dot is called spread operator and allows an iterable like an array to expand all it's elements
            [e.currentTarget.id]: e.currentTarget.value,
        });
    };

    const handleOnClickCreateAccount = () => {
        if (
            accountData.username === '' ||
            accountData.password === '' ||
            accountData.firstname === '' ||
            accountData.lastname === '' ||
            accountData.birthday === '' ||
            accountData.email === ''
        ) {
            return; // Can also do alert
        }
        handleCreateAccount(accountData);
        setAccountData(INITIAL_FORM_DATA);
    };

    const input = {
        data: [
            'Username',
            'Password',
            'First Name',
            'Last Name',
            'Birthday',
            'Email',
        ],
        label: [
            'Username',
            'At least 8 characters',
            'Your name',
            'Your last name',
            'Birthday',
            'user@email.com',
        ],
        page: '/signin',
        type: 'Sign Up',
        button: 'Create Account',
        footer: 'Already have an account?',
        span: 'Log In',
    };
    return (
        <LandingPageAuth
            textfieldNames={textfieldNames}
            input={input}
            handleOnClick={handleOnClickCreateAccount}
            handleForm={handleForm}
        />
    );
}

export default Signup;
