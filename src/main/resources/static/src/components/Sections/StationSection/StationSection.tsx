import * as React from "react";
import "./StationSection.scss";
import { IStationSectionProps, IStationSectionState } from "../../../types/StationTypes";
import { StationService } from "../../../services/StationService/StationService";
import ListGroupInfo from "../../ListGroup/ListGroupInfo";

export default class StationSection extends React.Component<IStationSectionProps, IStationSectionState> {

    constructor(props: IStationSectionProps) {
        super(props);

        this.loadStationsInfo = this.loadStationsInfo.bind(this);
        this.originSelection = this.originSelection.bind(this);

        this.state = {
            stations: []
        };
    }

    public componentDidMount(): void {
        this.loadStationsInfo();
    }

    private loadStationsInfo(): void {
        this.setState({stations: []});
        StationService.getStationsInfo().then(data => {
            this.setState({stations: data});
        });
    }

    private originSelection(station: string): void {
        console.log(station);
    }

    public render(): JSX.Element {

        return (
            <section className="stations-section container">
                <div className="row">
                    <div className="col-md-4">
                        <ListGroupInfo  label="stations" 
                                        input={this.state.stations}
                                        selectionCallback={this.originSelection} />
                    </div>

                    <div className="col-md-8">
                        <div className="panel">
                            test
                        </div>
                    </div>
                </div>
            </section>
        );

    }

}
