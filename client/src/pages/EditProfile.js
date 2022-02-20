import React from 'react';

// MUI
import { Grid, Card } from '@material-ui/core';

import EditProfileCurrentInfo from '../components/EditProfileCurrentInfo';
import EditProfileUpdateInfo from '../components/EditProfileUpdateInfo';

export default function EditProfile() {
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
                            <EditProfileCurrentInfo />
                        </Grid>
                        <Grid item xs={8} style={{ height: '100%' }}>
                            <EditProfileUpdateInfo />
                        </Grid>
                    </Grid>
                </Card>
            </Grid>
        </Grid>
    );
}
