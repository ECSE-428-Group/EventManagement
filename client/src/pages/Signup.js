import React, { useState, useEffect } from 'react';
import axios from 'axios';

// Components
import LandingPageAuth from '../components/LandingPageAuth';

// Router
import { useNavigate } from 'react-router-dom';

// Moment
import moment from 'moment';

const INITIAL_FORM_DATA = {
    username: '',
    password: '',
    firstname: '',
    lastname: '',
    birthday: '',
    email: '',
};

const INITIAL_FORM_ERRORS = {
    username: null,
    password: null,
    confirmpassword: null,
    firstname: null,
    lastname: null,
    birthday: null,
    email: null,
};

const textfieldNames = [
    'username',
    'password',
    'confirmPassword',
    'firstname',
    'lastname',
    'birthday',
    'email',
];

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
        'Repeat your password',
        'Your name',
        'Your last name',
        'YYYY-MM-DD',
        'user@email.com',
    ],
    page: '/signin',
    type: 'Sign Up',
    button: 'Create Account',
    footer: 'Already have an account?',
    span: 'Log In',
};

const baseUrl = 'http://localhost:8080';

function Signup({ handleCreateAccount }) {
    const navigate = useNavigate();

    const [accountData, setAccountData] = useState();
    const [usernames, setUsernames] = useState([]);
    const [formErrors, setFormErrors] = useState(INITIAL_FORM_ERRORS);

    useEffect(() => {
        checkUsername();
        checkPasswords();
        checkdate();
        checkEmail();
    }, [accountData]);

    useEffect(() => {
        getCurrentUsers().then((res) => {
            res.data.map((user) => {
                setUsernames((usernames) => [...usernames, user.username]);
            });
        });
    }, []);

    const handleForm = (e) => {
        setAccountData({
            ...accountData,
            [e.currentTarget.id]: e.currentTarget.value,
        });
    };

    const getCurrentUsers = () => {
        return axios
            .get(baseUrl + '/users')
            .then((users) => {
                return users;
            })
            .catch((error) => console.log(error));
    };

    const checkUsername = () => {
        if (accountData === undefined || usernames === undefined) {
            return;
        }

        if (usernames.includes(accountData.username)) {
            setFormErrors((p) => ({
                ...p,
                username: 'Username already in use',
            }));
        } else {
            setFormErrors((p) => ({
                ...p,
                username: null,
            }));
        }
    };

    const checkPasswords = () => {
        if (accountData === undefined || usernames === undefined) {
            return;
        }

        // Validate passwords
        if (accountData.password !== accountData.confirmPassword) {
            setFormErrors((p) => ({
                ...p,
                confirmpassword: 'Passwords do not match',
            }));
        } else {
            setFormErrors((p) => ({ ...p, confirmpassword: null }));
        }

        if (
            accountData.password === undefined ||
            accountData.password.length >= 6 ||
            accountData.password === ''
        ) {
            setFormErrors((p) => ({
                ...p,
                password: null,
            }));
        } else {
            setFormErrors((p) => ({
                ...p,
                password: 'Password must more than 6 characters',
            }));
        }
    };

    const checkdate = () => {
        if (accountData === undefined) {
            return;
        }

        const validDate = moment(
            accountData.birthday,
            'YYYY-MM-DD',
            true
        ).isValid();

        if (
            validDate ||
            accountData.birthday === '' ||
            accountData.birthday === undefined
        ) {
            setFormErrors((p) => ({
                ...p,
                birthday: null,
            }));
        } else {
            setFormErrors((p) => ({ ...p, birthday: 'Invalid Birthday' }));
        }
    };

    const checkEmail = () => {
        if (accountData === undefined) {
            return;
        }

        var re = /\S+@\S+\.\S+/;
        const validEmail = re.test(accountData.email);

        if (
            validEmail ||
            accountData.email === '' ||
            accountData.email === undefined
        ) {
            setFormErrors((p) => ({
                ...p,
                email: null,
            }));
        } else {
            setFormErrors((p) => ({
                ...p,
                email: 'Invalid email',
            }));
        }
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

        const status = handleCreateAccount(accountData);
        if (status === 0) {
            navigate('/userhome');
            localStorage.setItem('username', accountData.username);
            localStorage.setItem('password', accountData.password);
        }
        setAccountData(INITIAL_FORM_DATA);
    };

    return (
        <LandingPageAuth
            textfieldNames={textfieldNames}
            input={input}
            handleOnClick={handleOnClickCreateAccount}
            handleForm={handleForm}
            formErrors={formErrors}
        />
    );
}

export default Signup;
