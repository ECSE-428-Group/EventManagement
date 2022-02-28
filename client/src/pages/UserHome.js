import React from 'react';

import { Grid } from '@material-ui/core';

import Event from '../components/Event';
import NavBar from '../components/NavBar';

import ev1 from '../static/images/ev1.jpg';
import ev2 from '../static/images/ev2.jpg';
import ev3 from '../static/images/ev3.jpg';
import ev4 from '../static/images/ev4.jpg';
import ev5 from '../static/images/ev5.jpg';
import ev6 from '../static/images/ev6.jpg';
import ev7 from '../static/images/ev7.jpg';
import ev8 from '../static/images/ev8.jpg';
import home from '../static/images/userHome.jpg';

import '../index.css';

const EventData = [
    {
        image: ev1,
        title: 'Road Trip',
        description:
            'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ' +
            'labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation',
    },
    {
        image: ev2,
        title: 'Stargazing',
        description:
            'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ' +
            'labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation',
    },
    {
        image: ev3,
        title: 'Coffee Club',
        description:
            'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ' +
            'labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation',
    },
    {
        image: ev4,
        title: 'Camping',
        description:
            'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ' +
            'labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation',
    },
];

const EventData2 = [
    {
        image: ev5,
        title: 'Philosophy Discussions',
        description:
            'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ' +
            'labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation',
    },
    {
        image: ev6,
        title: 'Potluck',
        description:
            'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ' +
            'labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation',
    },
    {
        image: ev7,
        title: 'Grill Fest',
        description:
            'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ' +
            'labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation',
    },
    {
        image: ev8,
        title: 'Hiking',
        description:
            'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ' +
            'labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation',
    },
];

export default function UserHome() {
    return (
        <>
            <NavBar />
            <img src={home} alt='home' />
            <Grid container direction='row' alignItems='center' spacing={2}>
                {EventData.map((ev, index) => (
                    <Grid xs={3} item key={index}>
                        <Event
                            image={ev.image}
                            title={ev.title}
                            text={ev.description}
                        />
                    </Grid>
                ))}
            </Grid>
            <Grid container direction='row' alignItems='center' spacing={2}>
                {EventData2.map((ev, index) => (
                    <Grid xs={3} item key={index}>
                        <Event
                            image={ev.image}
                            title={ev.title}
                            text={ev.description}
                        />
                    </Grid>
                ))}
            </Grid>
        </>
    );
}
