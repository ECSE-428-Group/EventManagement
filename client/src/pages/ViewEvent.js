import React, { useEffect, useState } from 'react';

// MUI
import { Grid, Card } from '@material-ui/core';

// Components
import ViewEventInfo from '../components/ViewEventInfo';
import EventNameInfo from '../components/EventNameInfo';
import { getEvent } from './../API';
import { useParams } from 'react-router-dom';

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

export default function ViewEvent({}) {
    const params = useParams();

    const getEventDetails = (eventId) => {
        getEvent(eventId)
            .then(({ status, data }) => {
                if (status !== 200) {
                    // 200 indicates successful request
                    throw new Error('No event found');
                }
                setEventDetails(data);
            })
            .catch((error) => console.log(error));
        return 0;
    };

    useEffect(() => {
        getEventDetails(params.id);
    }, []);

    const [eventDetails, setEventDetails] = useState(INITIAL_EVENT_DETAIL);

    return (
        <Grid
            style={{ height: '100vh' }}
            container
            direction='row'
            justify='center'
            alignItems='center'
        >
            <Grid container direction='row' alignItems='center' item xs={10}>
                <Card
                    style={{
                        width: 'inherit',
                        boxShadow: 'none',
                        border: '1px solid #c4c4c4',
                    }}
                >
                    <Grid container direction='row' alignItems='center'>
                        <Grid item xs={4}>
                            <EventNameInfo eventDetails={eventDetails} />
                        </Grid>
                        <Grid item xs={8} style={{ height: '100%' }}>
                            <ViewEventInfo eventDetails={eventDetails} />
                        </Grid>
                    </Grid>
                </Card>
            </Grid>
        </Grid>
    );
}
