import {
    Card,
    CardMedia,
    CardContent,
    Button,
    Typography,
} from '@material-ui/core';
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const EventCard = (props) => {
    let navigate = useNavigate();
    const routeChange = () => {
        let path = `/viewEvent/${props.title}`;
        navigate(path);
    }
    return (
        <Card>
            <CardMedia component='img' image={props.image} />
            <CardContent>
                <Typography>{props.title}</Typography>
                <Typography>{props.text}</Typography>
                <Button onClick={routeChange} color={'primary'}>Read More</Button>
            </CardContent>
        </Card>
    );
};

export default EventCard;
