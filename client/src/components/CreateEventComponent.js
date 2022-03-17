import React from 'react';

// Libraries for forms
import { useFormik } from 'formik';
import * as Yup from "yup";

// MUI
import Grid from '@material-ui/core/Grid';
import Card from '@material-ui/core/Card';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';
import { FormControlLabel, RadioGroup } from '@material-ui/core';
import Radio from "@material-ui/core/Radio"

const today = new Date();

const validationSchema = Yup.object({
    date: Yup.date().required("This event has no date associated to it.").min(today, "This date has already passsed."),
    location: Yup.string().required("This event has no location associated to it.").nullable(),
    description: Yup.string().required("This event has no description associated to it.").nullable(),
    image: Yup.mixed().required("This event has no image associated to it."),
});

export default function CreateEventComponent() {
    const formik = useFormik({
        initialValues: {
            date: "",
            isPrivate: null,
            isVirtual: null,
            location: "",
            description: "",
            image: null
        },
        validationSchema: validationSchema,
        onSubmit: values => {
            fetch(`https://event-management-app-backend.herokuapp.com/event?date=${formik.values.date.replace("T", " ")}:00&isPrivate=${formik.values.isPrivate}&isVirtual=${formik.values.isVirtual}&location=${formik.values.location}&description=${formik.values.description}&image=${formik.values.image}`, {
                method: "POST",
                mode: "no-cors",
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
            .then(res => {
                if(res.status !== 200 && res.status !== 201) {
                    alert("Failed!");
                } else {
                    alert("Event successfully created!");
                }
            })
            .catch(err => {
                console.log(err);
            })
        }
    });

    return (
        <Grid
            style={{ height: '100vh' }}
            container
            direction='row'
            justify='center'
            alignItems='center'
        >
            <Card style={{ boxShadow: 'none', border: '1px solid #c4c4c4' }}>
                <Grid
                    style={{ padding: 50 }}
                    container
                    direction='row'
                    justify='center'
                    alignItems='center'
                >
                    <Grid item xs={12}>
                        <Grid>
                            <Typography
                                variant='h5'
                                component='h1'
                                gutterBottom
                            >
                                Create Event
                            </Typography>
                            <Typography variant='body2'>
                                Enter your details below to get started on
                                Joinit
                            </Typography>
                        </Grid>

                        <Grid style={{ padding: '10px 0px' }}>
                            <form onSubmit={formik.handleSubmit}>
                                <Grid style={{padding: '5px 0px'}}>
                                    <Typography gutterBottom variant='body2'>
                                        Event Date
                                    </Typography>
                                    <TextField
                                        name="date"
                                        value={formik.values.date}
                                        type="datetime-local"
                                        onChange={formik.handleChange}
                                        variant='outlined'
                                        fullWidth
                                        size='small'
                                        inputProps={{
                                            style: {
                                                fontSize: 12,
                                            },
                                        }}
                                        InputLabelProps={{
                                            style: {
                                                fontSize: 12,
                                            }
                                        }}
                                        error={formik.touched.date && Boolean(formik.errors.date)}
                                        helperText={formik.touched.date && formik.errors.date}
                                    />
                                    
                                </Grid>

                                <Grid style={{padding: '5px 0px'}}>
                                    <Typography gutterBottom variant='body2'>
                                        Location
                                    </Typography>
                                    <TextField
                                        name="location"
                                        value={formik.values.location}
                                        onChange={formik.handleChange}
                                        variant='outlined'
                                        fullWidth
                                        size='small'
                                        inputProps={{
                                            style: {
                                                fontSize: 12,
                                            },
                                        }}
                                        InputLabelProps={{
                                            style: {
                                                fontSize: 12,
                                            }
                                        }}
                                        error={formik.touched.location && Boolean(formik.errors.location)}
                                        helperText={formik.touched.location && formik.errors.location}
                                    />
                                </Grid>

                                <Grid style={{padding: '5px 0px'}}>
                                    <Typography gutterBottom variant='body2'>
                                        Description
                                    </Typography>
                                    <TextField
                                        name="description"
                                        value={formik.values.description}
                                        onChange={formik.handleChange}
                                        variant='outlined'
                                        fullWidth
                                        multiline
                                        rows={3}
                                        inputProps={{
                                            style: {
                                                fontSize: 12,
                                            },
                                        }}
                                        InputLabelProps={{
                                            style: {
                                                fontSize: 12,
                                            }
                                        }}
                                        error={formik.touched.description && Boolean(formik.errors.description)}
                                        helperText={formik.touched.description && formik.errors.description}
                                    />
                                </Grid>

                                <Grid style={{padding: '5px 0px'}}>
                                    <Typography gutterBottom variant='body2'>
                                        Image
                                    </Typography>
                                    <TextField
                                        name="image"
                                        value={formik.values.image}
                                        onChange={formik.handleChange}
                                        variant='outlined'
                                        fullWidth
                                        size='small'
                                        type="file"
                                        inputProps={{
                                            style: {
                                                fontSize: 12,
                                            },
                                        }}
                                        InputLabelProps={{
                                            style: {
                                                fontSize: 12,
                                            }
                                        }}
                                        error={formik.touched.image && Boolean(formik.errors.image)}
                                        helperText={formik.touched.image && formik.errors.image}
                                    />
                                </Grid>
                                    
                                <Grid style={{padding: '5px 0px'}}>
                                    <Typography gutterBottom variant='body2'>
                                        Private Event?
                                    </Typography>
                                    <RadioGroup
                                        row
                                        name="isPrivate"
                                        value={formik.values.isPrivate}
                                        onChange={formik.handleChange}
                                    >
                                        <FormControlLabel
                                        value="false"
                                        control={<Radio />}
                                        label="No"
                                        />
                                        <FormControlLabel 
                                        value="true"
                                        control={<Radio />}
                                        label="Yes"
                                        />
                                    </RadioGroup>
                                </Grid>

                                <Grid style={{padding: '5px 0px'}}>
                                    <Typography gutterBottom variant='body2'>
                                        Virtual Event?
                                    </Typography>
                                    <RadioGroup
                                        row
                                        name="isVirtual"
                                        value={formik.values.isVirtual}
                                        onChange={formik.handleChange}
                                    >
                                        <FormControlLabel
                                        value="false"
                                        control={<Radio />}
                                        label="No"
                                        />
                                        <FormControlLabel 
                                        value="true"
                                        control={<Radio />}
                                        label="Yes"
                                        />
                                    </RadioGroup>
                                </Grid>

                                <Button
                                    type="submit"
                                    size='small'
                                    fullWidth
                                    style={{
                                    whiteSpace: 'nowrap',
                                    minWidth: 'max-content',
                                    backgroundColor: '#6558f5',
                                    color: '#ffffff',
                                    }}
                                >
                                Create Event
                                </Button>

                            </form>
                        </Grid>
                    </Grid>
                    
                </Grid>
                <Grid
                    container
                    direction='row'
                    justify='flex-end'
                    alignItems='center'
                >
                </Grid>
            </Card>
        </Grid>
    );
}
