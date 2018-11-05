import * as React from "react";
import "./ActivityFeed.css";
import { Media } from 'reactstrap';
import {IEstimate} from "../../types/StationTypes";
import {IActivityFeedProps, IActivityFeedState} from "../../types/ActivityTypes";

export default class ActivityFeed extends React.Component<IActivityFeedProps, IActivityFeedState> {

    constructor(props: IActivityFeedProps) {
        super(props);

        this.state = {
            feed: []
        };
    }

    componentDidMount(): void {
        this.setState({});
    }

    componentWillReceiveProps(nextProps: Readonly<IActivityFeedProps>): void {
        if (nextProps.feed) {
            this.setState({feed: nextProps.feed});
        }
    } 

    generateActivityRows(rows: IEstimate[]): JSX.Element[] {
        return rows.map((row: IEstimate, index: number) => {
            return (
                <Media key={"feed-" + index}>
                    <Media body={true} key={"feed-body-" + index}>
                        <Media heading={true} key={"feed-heading" + index}>
                            {row}
                        </Media>
                        Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
                    </Media>
                </Media>
            );
        });
    }

    render(): JSX.Element {
        return (
            <section className="activity-feed">
                {this.generateActivityRows(this.state.feed)}
            </section>
        );
    }


}