import React, { useEffect, useState } from 'react';

// MUI
import { Button, Grid } from '@material-ui/core';
import { getEvent } from './../API';
import { useParams } from 'react-router-dom';

const input = {
    title: 'Information about this event:',
    space: '  ',
    description:
        'This is the event description, here you can find a description of the event. In this description, it will be described what the event is about. In this event, you will have access to a series of activities with the purpose of helping you meet new people. Have fun',
};

const INITIAL_EVENT_DETAIL = {
    date: '',
    isPrivate: false,
    isVirtual: false,
    location: '',
    description: '',
    image: '',
    tags: [],
    organizers: [],
    attendees: [],
    posts: [],
};

export default function ViewEventInfo({}) {
    const params = useParams();
    const getEventDetails = (eventId) => {
        getEvent(eventId)
            .then(({ status, data }) => {
                if (status !== 200) {
                    // 200 indicates successful request
                    throw new Error('Account not created');
                }
                setEventDetails(data);
                console.log(data);
            })
            .catch((error) => console.log(error));
        return 0;
    };

    useEffect(() => {
        getEventDetails(params.id);
    }, []);

    const [eventDetails, setEventDetails] = useState(INITIAL_EVENT_DETAIL);
    return (
        <>
            <Grid
                container
                direction='row'
                justify='flex-start'
                alignItems='center'
                style={{ margin: 30 }}
                item
                xs={10}
            >
                <Grid item xs={10}>
                    {eventDetails.description}
                </Grid>
            </Grid>
            <Grid
                container
                direction='row'
                justify='flex-start'
                alignItems='center'
                style={{ margin: 30 }}
                item
                xs={10}
            >
                <Grid item xs={10}>
                    {input.space}
                </Grid>
            </Grid>
            <Grid
                container
                direction='row'
                justify='flex-start'
                alignItems='center'
                style={{ margin: 30 }}
                item
                xs={10}
            >
                <Grid item xs={10}>
                    {input.description}
                </Grid>
            </Grid>
            <Grid
                style={{ padding: '60px 10px 0px 0px' }}
                container
                justifyContent='flex-end'
                alignItems='flex-end'
            >
                <Button
                    variant='outlined'
                    style={{ backgroundColor: '#6558f5', color: '#ffffff' }}
                >
                    Exit
                </Button>
            </Grid>
        </>
    );
}
