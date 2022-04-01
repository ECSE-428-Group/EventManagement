import React from 'react';

// MUI
import { Grid, Card } from '@material-ui/core';

// Components
import ViewEventInfo from '../components/ViewEventInfo';
import EventNameInfo from '../components/EventNameInfo';

export default function ViewEvent() {
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
                            <EventNameInfo />
                        </Grid>
                        <Grid item xs={8} style={{ height: '100%' }}>
                            <ViewEventInfo/>
                        </Grid>
                    </Grid>
                </Card>
            </Grid>
        </Grid>
    )
}