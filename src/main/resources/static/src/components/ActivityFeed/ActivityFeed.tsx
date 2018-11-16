import * as React from "react";
import "./ActivityFeed.css";
import {IEtd, IEstimate} from "../../types/StationTypes";
import {IActivityFeedProps, IActivityFeedState} from "../../types/ActivityTypes";
import {Button, Card } from "reactstrap";

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

    generateEstimates(estimates: IEstimate[]): JSX.Element[] {
        return estimates.map((estimate: IEstimate, index) => {
            return (
                <Card key={"estimate-card" + index}>
                    <div key={"estimate-card-body" + index}>
                        {estimate.minutes}
                    </div>
                </Card>
            );
        });
    }

    generateActivityRows(rows: IEtd[]): JSX.Element[] {
        return rows.map((etd: IEtd, index: number) => {
            return (
                <div key={"etd-body"+index}>
                    <Button key={"est" + index} color="primary" id="toggler" style={{ marginBottom: '1rem' }}>
                        {etd.abbreviation} - {etd.destination}
                    </Button>
                    {/* <UncontrolledCollapse key={"toggle" + index} toggler="#toggler"> */}
                        {this.generateEstimates(etd.estimates)}
                    {/* </UncontrolledCollapse> */}
                </div>
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