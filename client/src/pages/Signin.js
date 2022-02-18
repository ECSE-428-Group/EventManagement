import React from 'react';

// Components
import LandingPageAuth from '../components/LandingPageAuth';

export default function Signin() {
    const input = {
        data: ['Email', 'Password'],
        label: ['name@email.com', 'Password'],
        page: '/signup',
        type: 'Sign In',
        button: 'Login',
        footer: "Don't have an account?",
        span: 'Join Us!',
    };
    return <LandingPageAuth input={input} />;
}
