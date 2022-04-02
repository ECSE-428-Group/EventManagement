import React from 'react';
import axios from 'axios';
import { Grid } from '@material-ui/core';

import EventCard from '../components/EventCard';
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
    const [events, setEvents] = React.useState([]);

    React.useEffect(() => {
        axios.get("http://localhost:8080/getAll/event")
            .then((res) => { setEvents(res.data.sort((a, b) => (b.eventId > a.eventId) ? 1 : ((a.eventId > b.eventId) ? -1 : 0))); });
    }, []);
    return (
        <>
            <NavBar />
            <img src={home} alt='home' />
            <Grid container direction='row' alignItems='center' spacing={2}>
                {events.map((ev, index) => (
                    <Grid xs={3} item key={index}>
                        <EventCard
                            image={EventData[index % EventData.length].image}
                            title={ev.eventId}
                            text={ev.description}
                        />
                    </Grid>
                ))}
            </Grid>
        </>
    );
}
