import * as React from "react";
import { IStationSectionProps, IStationSectionState } from "../../../types/StationTypes";
import { StationService } from "../../../services/StationService/StationService";
import { IStation, IEtd } from "../../../types/StationTypes"; 
import ActivityFeed from "../../ActivityFeed/ActivityFeed";

export default class StationSection extends React.Component<IStationSectionProps, IStationSectionState> {

    constructor(props: IStationSectionProps) {
        super(props);
        
        this.state = {
            abbr: props.match.params.abbr,
            station: undefined
        };
    } 

    public componentDidMount(): void {
        StationService.getStationsEstimates(this.state.abbr).then((data: IStation[]) => {
            this.setState({station: data[0]});
        });
    }

    private generateFeed(): IEtd[] {
        if (this.state.station) {
            return this.state.station.etds;
        } else {
            return [];
        }
    }

    public render(): JSX.Element {
        return (
            <ActivityFeed feed={this.generateFeed()} />
        );
    }


}