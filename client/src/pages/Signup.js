import React from 'react';

// Components
import LandingPageAuth from '../components/LandingPageAuth';

export default function Signup() {
    const input = {
        data: ['Name', 'Email', 'Password', 'Confirm password'],
        label: [
            'Your name',
            'name@email.com',
            'At least 8 characters',
            'Enter password again',
        ],
        page: '/signin',
        type: 'Sign Up',
        button: 'Create Account',
        footer: 'Already have an account?',
        span: 'Log In',
    };
    return <LandingPageAuth input={input} />;
}
