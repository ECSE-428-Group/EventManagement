import React, { useState } from 'react';

// Components
import LandingPageAuth from '../components/LandingPageAuth';

// Router
import { useNavigate } from 'react-router-dom';

const INITIAL_FORM_DATA = {
    username: '',
    password: '',
    firstname: '',
    lastname: '',
    birthday: '',
    email: '',
};

function Signup({ handleCreateAccount }) {
    const navigate = useNavigate();

    const [accountData, setAccountData] = useState();

    const textfieldNames = [
        'username',
        'password',
        'confirmPassword',
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
            accountData.confirmPassword === '' ||
            accountData.firstname === '' ||
            accountData.lastname === '' ||
            accountData.birthday === '' ||
            accountData.email === ''
        ) {
            return; // Can also do alert
        }
        if (accountData.password !== accountData.confirmPassword) {
            alert('Passwords do not match');
            return;
        }
        if (accountData.password.length < 6) {
            alert('Passwords must be at least 6 characters');
            return;
        }
        const status = handleCreateAccount(accountData);
        if (status == 0) {
            navigate('/userhome');
        }
        setAccountData(INITIAL_FORM_DATA);
    };

    const input = {
        data: [
            'Username',
            'Password',
            'Confirm password',
            'First Name',
            'Last Name',
            'Birthday',
            'Email',
        ],
        label: [
            'Username',
            'At least 6 characters',
            'At least 6 characters',
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
